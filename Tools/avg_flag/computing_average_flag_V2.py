from os import listdir
from PIL import Image
import numpy as np

def avg(alist):
    return sum(alist) / len(alist)

def main():

    IMAGE_PATH = "C:\\Users\\carli\\Documents\\AA_My_Stuff\\Projects\\Flag-App\\Tools\\flags"
    OUTPUT_WIDTH = 2560
    OUTPUT_HEIGHT = 3121
    # height, width, ignore = array.shape

    # Create array for final image
    output_img_array = np.zeros((OUTPUT_HEIGHT, OUTPUT_WIDTH, 3))
    pixel_counts = np.zeros((OUTPUT_HEIGHT, OUTPUT_WIDTH))

    flag_count = 0
    for filename in listdir(IMAGE_PATH):
        
        # Save progress every 10 flags
        flag_count += 1
        if flag_count % 10 == 0:
            img = Image.fromarray(output_img_array.astype(np.uint8))
            img.save("output/output%d.png" % flag_count)
            print("Output saved after %d flags processed" % flag_count)

        # Load image into array
        print("Processing %s..." % filename)
        current_img_array = np.asarray(Image.open(IMAGE_PATH + "\\" + filename).convert("RGB"))

        # Get shape
        height, width, ignore = current_img_array.shape

        # Calculate shift
        x_shift = (OUTPUT_WIDTH - width) // 2
        y_shift = (OUTPUT_HEIGHT - height) // 2

        # Iterate though all pixels
        for y in range(height):
            for x in range(width):

                # Update pixel count
                pixel_counts[y + y_shift][x + x_shift] += 1

                # Update average for each r, g, and b value
                # new_avg = old_avg + (new_val - old_avg) / new_count
                for i in range(3):
                    old_avg = output_img_array[y + y_shift][x + x_shift][i]
                    new_val = current_img_array[y][x][i]
                    new_count = pixel_counts[y + y_shift][x + x_shift]
                    
                    output_img_array[y + y_shift][x + x_shift][i] = old_avg + (new_val - old_avg) / new_count
    
    # Save image
    img = Image.fromarray(output_img_array.astype(np.uint8))
    img.save("output/final_output.png")

if __name__ == "__main__":
    main()