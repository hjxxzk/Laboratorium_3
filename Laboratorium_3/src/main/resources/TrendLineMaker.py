import sqlite3
import sys
import matplotlib.pyplot as plt
import pandas as pd


def read_from_database(employee_id, start_date, ending_date, db_path):
    connection = sqlite3.connect(db_path)
    cursor = connection.cursor()
    params = (employee_id, start_date, ending_date)
    cursor.execute("SELECT date, type, weight FROM lab3 WHERE id = ? AND date BETWEEN ? AND ? ORDER BY date", params)
    feedback = cursor.fetchall()
    connection.close()

    return feedback


employee_id = int(sys.argv[1])
start_date = sys.argv[2]
ending_date = sys.argv[3]
db_path = sys.argv[4]

data = read_from_database(employee_id, start_date, ending_date, db_path)
df = pd.DataFrame(data, columns=['date', 'type', 'weight'])

df['date'] = pd.to_datetime(df['date'])

positive_comments = df[df['type'] == 'Positive']
negative_comments = df[df['type'] == 'Negative']

positive_trend = positive_comments.groupby('date')['weight'].mean().reset_index()
negative_trend = negative_comments.groupby('date')['weight'].mean().reset_index()

plt.figure(figsize=(10, 6))

plt.plot(positive_trend['date'], positive_trend['weight'], label='Positive Trend', marker='o')
plt.plot(negative_trend['date'], negative_trend['weight'], label='Negative Trend', marker='o')

plt.title('Analysis of Trend Lines for Positive and Negative Comments')
plt.xlabel('Date')
plt.ylabel('Average Weight')
plt.legend()
plt.grid(True)
plt.show()
