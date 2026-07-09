from os import listdir, rename

for filename in listdir("."):
    if filename.endswith(".png"):
        filename = filename.split(".")[0]
        rename(filename + ".png", "flag_" + filename.lower() + ".png")
    