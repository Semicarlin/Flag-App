from os import listdir
from PIL import Image
import numpy as np

def avg(alist):
    return sum(alist) / len(alist)

def main():

    IMAGE_PATH = "C:\\Users\\carli\\Documents\\AA_My_Stuff\\Projects\\Flag-App\\Tools\\flags"

    # Load all images
    image_arrays: np.array = []
    i = 0
    for filename in listdir(IMAGE_PATH):
        image_arrays.append(np.asarray(Image.open(IMAGE_PATH + "\\" + filename).convert("RGB")))
        i += 1
        # if i > 10: break

    # Determine shape of image
    # width, height, three = np.array.shape
    max_width = -1
    max_height = -1
    for array in image_arrays:
        width, height, ignore = array.shape
        if width > max_width: max_width = width
        if height > max_height: max_height = height
    print("Shape of flag is %d x %d" % (width, height))
    input("Press enter to proceed")

    # Construct image
    img_array = np.zeros((max_width, max_height, 3))
    # for array in image_arrays:
    #     width, height, ignore = array.shape
    #     x_scale = (max_width - width) // 2
    #     y_scale = (max_height - height) // 2

    #     for x in range(width):
    #         for y in range(height):
    #             img_array[x + x_scale, y + y_scale] = array[x, y]

    img_matrix = [[[] for x in range(max_width)] for y in range(max_height)]
    for i in range(len(image_arrays)):
        print("Pulling RGB values from flag %d" % i)
        array = image_arrays[i]
        width, height, ignore = array.shape
        x_scale = (max_width - width) // 2
        y_scale = (max_height - height) // 2


        for x in range(width):
            for y in range(height):
                img_matrix[y + y_scale][x + x_scale].append(array[x, y])

    # Compute averages
    for y in range(max_height):
        print("Computing average for row %d..." % y)
        for x in range(max_width):
            img_array[x, y] = avg(img_matrix[y][x])

    
    # Save image
    img = Image.fromarray(img_array.astype(np.uint8))
    img.save("output.png")

if __name__ == "__main__":
    main()