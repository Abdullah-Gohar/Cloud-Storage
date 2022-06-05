with open("q.sql",'w') as file:
    for i in range(1,100):
        file.write(f"Insert into Client values('abdullah{i}', 'password', 'ag@gmail.com', 'Shughal', 1, 0, default)\n")