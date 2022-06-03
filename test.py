with open("dump\\data.txt",'w') as file:
    for i in range(1_000_000):
        file.write(f"{i}\n")