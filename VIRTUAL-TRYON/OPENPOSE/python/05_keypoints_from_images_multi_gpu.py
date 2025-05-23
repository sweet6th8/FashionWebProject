# From Python
# It requires OpenCV installed for Python
import argparse
import os
import sys
import time

import cv2

try:
    # Import Openpose (Windows/Ubuntu/OSX)
    dir_path = os.path.dirname(os.path.realpath(__file__))
    try:
        # Change these variables to point to the correct folder (Release/x64 etc.)
        sys.path.append(dir_path + '/../bin/python/openpose/Release');
        os.environ['PATH']  = os.environ['PATH'] + ';' + dir_path + '/../x64/Release;' +  dir_path + '/../bin;'
        import pyopenpose as op
    except ImportError as e:
        print('Error: OpenPose library could not be found. Did you enable `BUILD_PYTHON` in CMake and have this Python script in the right folder?')
        raise e

    # Flags
    parser = argparse.ArgumentParser()
    parser.add_argument("--image_dir", default="../examples/media/", help="Process a directory of images. Read all standard formats (jpg, png, bmp, etc.).")
    parser.add_argument("--no_display", default=False, help="Enable to disable the visual display.")
    parser.add_argument("--num_gpu", default=op.get_gpu_number(), help="Number of GPUs.")
    args = parser.parse_known_args()

    # Custom Params (refer to include/openpose/flags.hpp for more parameters)
    params = dict()
    params["model_folder"] = "../models/"
    params["num_gpu"] = int(vars(args[0])["num_gpu"])
    numberGPUs = int(params["num_gpu"])

    # Add others in path?
    for i in range(0, len(args[1])):
        curr_item = args[1][i]
        if i != len(args[1])-1: next_item = args[1][i+1]
        else: next_item = "1"
        if "--" in curr_item and "--" in next_item:
            key = curr_item.replace('-','')
            if key not in params:  params[key] = "1"
        elif "--" in curr_item and "--" not in next_item:
            key = curr_item.replace('-','')
            if key not in params: params[key] = next_item

    # Construct it from system arguments
    # op.init_argv(args[1])
    # oppython = op.OpenposePython()

    # Starting OpenPose
    opWrapper = op.WrapperPython()
    opWrapper.configure(params)
    opWrapper.start()

    # Read frames on directory
    imagePaths = op.get_images_on_directory(args[0].image_dir);

    # Read number of GPUs in your system
    start = time.time()

    # Process and display images
    for imageBaseId in range(0, len(imagePaths), numberGPUs):

        # Create datums
        images = []

        # Read and push images into OpenPose wrapper
        for gpuId in range(0, numberGPUs):

            imageId = imageBaseId+gpuId
            if imageId < len(imagePaths):

                imagePath = imagePaths[imageBaseId+gpuId]
                datum = op.Datum()
                images.append(cv2.imread(imagePath))
                datum.cvInputData = images[-1]
                opWrapper.waitAndEmplace(op.VectorDatum([datum]))

        # Retrieve processed results from OpenPose wrapper
        for gpuId in range(0, numberGPUs):

            imageId = imageBaseId+gpuId
            if imageId < len(imagePaths):

                datums = op.VectorDatum()
                opWrapper.waitAndPop(datums)
                datum = datums[0]

                print("Body keypoints: \n" + str(datum.poseKeypoints))

                if not args[0].no_display:
                    cv2.imshow("OpenPose 1.7.0 - Tutorial Python API", datum.cvOutputData)
                    key = cv2.waitKey(15)
                    if key == 27: break

    end = time.time()
    print("OpenPose demo successfully finished. Total time: " + str(end - start) + " seconds")
except Exception as e:
    print(e)
    sys.exit(-1)
