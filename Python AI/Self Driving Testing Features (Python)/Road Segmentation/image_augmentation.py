import numpy as np
import random
import cv2
from os.path import join, expanduser
import matplotlib.pyplot as plt


def perform_augmentation(batch_x, batch_y):
       def mirror(x):
        return x[:, ::-1, :]
        
def augment_in_hsv_space(x_hsv):
    x_hsv = np.float32(cv2.cvtColor(x_hsv, cv2.COLOR_RGB2HSV))
    x_hsv[:,:,0] = x_hsv[:,:,0] * random.uniform(0.9, 1.1)    #changes Hue
    x_hsv[:,:,1] = x_hsv[:,:,1] * random.uniform(0.5, 2.0)    #changes Saturation
    x_hsv[:,:,2] = x_hsv[:,:,2] * random.uniform(0.5, 2.0)    #changes Brightness
    x_hsv = np.unit8(np.clip(x_hsv, 0, 255))
    return cv2.cvtColor(x_hsv, cv2.COLOR_HSV2RGB)
        
    batch_x_aug = np.copy(batch_x)
    batch_y_aug = np.copy(batch_y)

    for b in range(batch_x_aug.shape[0]):

        # Random mirroring
        should_mirror = random.choice([True, False])
        if should_mirror:
            batch_x_aug[b] = mirror(batch_x[b])
            batch_y_aug[b] = mirror(batch_y[b])

        # Random change in image values (hue, saturation, brightness)
        batch_x_aug[b] = augment_in_hsv_space(batch_x_aug[b])

    return batch_x_aug, batch_y_aug


def debug_visualize_data_augmentation():
    
    from helper import gen_batch_function
#    from main_27 import gen_batch_function  # keep here to avoid circular dependencies

    # Parameters
    data_dir = join(expanduser("~"), 'code', 'self-driving-car', 'project_12_road_segmentation', 'data')
    image_h, image_w = (160, 576)
    batch_size = 20

    # Create function to get batches
    batch_generator = gen_batch_function(join(data_dir, 'data_road/training'), (image_h, image_w))

    # Load a batch and augment it
    batch_x, batch_y = next(batch_generator(batch_size))
    batch_x_aug, batch_y_aug = perform_augmentation(batch_x, batch_y)

    # Show both original and augmented batch images
    for i in range(batch_size):
        plt.figure(1)

        x = batch_x[i]
        y = np.uint8(batch_y[i][:, :, 1]) * 255  # cast from boolean to uint8 for visualization
        y = np.stack([y, y, y], axis=2)          # turn to 3-channels for visualization
        xy = np.concatenate([x, y], axis=0)

        plt.imshow(xy)

        plt.figure(2)

        x_aug = batch_x_aug[i]
        y_aug = np.uint8(batch_y_aug[i][:, :, 1]) * 255  # cast from boolean to uint8 for visualization
        y_aug = np.stack([y_aug, y_aug, y_aug], axis=2)  # turn to 3-channels for visualization
        xy_aug = np.concatenate([x_aug, y_aug], axis=0)

        plt.imshow(xy_aug)

        plt.show(block=False)
        plt.waitforbuttonpress()
