import hashlib
text = 'hanniiii1006'
algorithm = hashlib.sha256()
algorithm.update(text.encode(encoding='UTF-8'))
print(algorithm.hexdigest(), text)