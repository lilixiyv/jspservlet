import secrets
import random
import string

def generate_random_phone_number():
    # 生成剩下的10位数字
    rest_digits = ''.join([str(random.randint(0, 9)) for _ in range(10)])
    return '1' + rest_digits

def generate_random_hex():
    random_number = secrets.randbits(256)  # 生成256位的随机数
    return format(random_number, '064x')  # 转换为16进制表示，总共64个字符

def generate_random_string(length):
    characters = string.ascii_letters + string.digits  # 包含字母和数字的字符集
    return ''.join(random.choice(characters) for i in range(length))

# 生成长度为8-16位的字符串
print(generate_random_string(random.randint(8, 16)))
print(generate_random_hex())
print(generate_random_phone_number())