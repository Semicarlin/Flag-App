from os import listdir

def main():
    country_code_list = []
    with open("../formatting_database/output.csv") as file:
        country_code_list = [line.strip().split("\t")[1].strip("\"").lower() for line in file.readlines()]


        for filename in listdir("w2560"):
            if filename[:-4] not in country_code_list:
                print(filename)

if __name__ == "__main__":
    main()