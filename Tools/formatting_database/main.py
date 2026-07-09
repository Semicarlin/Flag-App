def main():
    formatted_content = ""
    with open("content.txt", "r") as file:
        for line in file.readlines():
            formatted_content += "\t".join(["\"%s\"" % token.strip().split("(")[0] for token in line.split("\t")[:2]]) + "\n"
    with open("output.csv", "w") as file:
        file.write(formatted_content)


if __name__ == "__main__":
    main()