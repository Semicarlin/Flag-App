def main():
    continents_dict = {}
    with open("continents.csv") as file:
        for line in file.readlines():
            try:
                code, country, continent, un_bool = line.split(",")
            except ValueError:
                print("Error with: " + line)
                continue
            else:
                continents_dict[code.lower()] = continent

    formatted_content = ""
    with open("../formatting_database/output.csv") as file:
        for line in file.readlines():
            name, code = line.strip().split("\t")
            formatted_content += "new Flag(%s, %s, %s)" % (name, code, (continents_dict[code.lower()] if code.lower() in continents_dict else "\"Unsure\"")) + ",\n"
    with open("output.txt", "w") as file:
        file.write(formatted_content)

if __name__ == "__main__":
    main()