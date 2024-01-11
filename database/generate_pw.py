import random
import csv
import string

# 生成100个十位以内的字符，只由数字和字母组成
data = [''.join(random.choices(string.ascii_letters + string.digits, k=10)) for _ in range(100)]

# 将数据输出至一个csv文件
with open('random_characters.csv', 'w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(['Random Characters'])
    for item in data:
        writer.writerow([item])