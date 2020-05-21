# -*- coding: utf-8 -*-

import os
import tempfile
import heapq
import sys


class heapnode:
    """ Heapnode of a Heap (MinHeap Here)
       @params
               item        The actual value to be stored in heap
               fileHandler The filehandler of the file that stores the number"""

    def __init__(
            self,
            item,
            fileHandler,
    ):
        self.item = item
        self.fileHandler = fileHandler


class externalMergeSort:
    """ Splits the large file into small files ,sort the small files and uses python
        heapq module to merge the different small sorted files.  Each sorted files is
        loaded as a  generator ,hence won't loads entire data into memory """
    """ @params
           sortedTempFileHandlerList - List of all filehandlers to all temp files formed by splitting large files
    """

    def __init__(self):
        self.sortedTempFileHandlerList = []
        self.getCurrentDir()

    def getCurrentDir(self):
        self.cwd = os.getcwd()

    """ Iterates the sortedCompleteData Generator """

    def iterateSortedData(self, sortedCompleteData):
        for line in sortedCompleteData:
            print(line)

    def createSortedFile(self, sortedCompleteData):
        with open('sortedLargeFile.txt', 'w') as file:
            file.write('\n'.join(str(data) for data in sortedCompleteData))

    """ HighLevel Pythonic way to sort all numbers in the list of files that are pointed by Filehandlers of sortedTempFileHandlerList """

    def mergeSortedtempFiles(self):
        mergedNo = (map(int, tempFileHandler) for tempFileHandler in
                    self.sortedTempFileHandlerList)  # mergedNo is a generator which stores all the sorted number in ((1,4,6),(3,7,8)...) format. Since it's generator ,it doesn't stores in memory and do lazy allocation
        sortedCompleteData = heapq.merge(
            *mergedNo)  # uses python heapqmodule that takes a list of sorted iterators and sort it and generates a sorted iterator , So again no more storing of data in memory
        return sortedCompleteData

    """ min heapify function """

    def heapify(
            self,
            arr,
            i,
            n,
    ):
        left = 2 * i + 1
        right = 2 * i + 2
        if left < n and arr[left].item < arr[i].item:
            smallest = left
        else:
            smallest = i

        if right < n and arr[right].item < arr[smallest].item:
            smallest = right

        if i != smallest:
            (arr[i], arr[smallest]) = (arr[smallest], arr[i])
            self.heapify(arr, smallest, n)

    """ construct heap """

    def construct_heap(self, arr):
        l = len(arr) - 1
        mid = l / 2
        while mid >= 0:
            self.heapify(arr, mid, l)
            mid -= 1

    """ low level implementation to merge k sorted small file to a larger file . Move first element of all files to a min heap . The Heap has now the smallest element .
         Mmoves  that element from heap to a file . Get the filehandler of that element .Read the next element using the  same filehandler . If next file element is empty, mark it as INT_MAX.
         Moves it to heap . Again Heapify . Continue this until all elements of heap is INT_MAX or all the smaller files have read fully """

    def mergeSortedtempFiles_low_level(self):
        list = []
        sorted_output = []
        for tempFileHandler in self.sortedTempFileHandlerList:
            item = int(tempFileHandler.readline().strip())
            list.append(heapnode(item, tempFileHandler))

        self.construct_heap(list)
        while True:
            min = list[0]
            if min.item == sys.maxint:
                break
            sorted_output.append(min.item)
            fileHandler = min.fileHandler
            item = fileHandler.readline().strip()
            if not item:
                item = sys.maxint
            else:
                item = int(item)
            list[0] = heapnode(item, fileHandler)
            self.heapify(list, 0, len(list))
        return sorted_output

    def deleteFolder(self, dirName):
        import os
        import shutil
        folder = dirName
        if os.path.exists(folder):
            for filename in os.listdir(folder):
                file_path = os.path.join(folder, filename)
                try:
                    if os.path.isfile(file_path) or os.path.islink(file_path):
                        os.unlink(file_path)
                    elif os.path.isdir(file_path):
                        shutil.rmtree(file_path)
                except Exception as e:
                    print('Failed to delete %s. Reason: %s' % (file_path, e))
        else:
            os.mkdir(folder)
        print("deleted any existing temp files.")

    """ function to Split a large files into smaller chunks , sort them and store it to temp files on disk"""

    def splitFiles(self, largeFileName, smallFileSize):
        tempDir = self.cwd + '\\temp'
        print(tempDir)
        self.deleteFolder(tempDir)

        with open(largeFileName, 'r') as largeFileHandler:
            tempBuffer = []
            size = 0
            for line in largeFileHandler:
                tempBuffer.append(line)
                size += 1
                if size % smallFileSize == 0:
                    tempBuffer = sorted(tempBuffer, key=lambda tempBufferLine:
                                        int(tempBufferLine.strip()))

                    tempFile = tempfile.NamedTemporaryFile(dir=tempDir, delete=False)
                    for data in tempBuffer:
                        tempFile.write(bytes(data, encoding = 'utf-8'))
                    tempFile.seek(0)
                    self.sortedTempFileHandlerList.append(tempFile)

                    tempBuffer = []


if __name__ == '__main__':
    largeFileName = 'largefile'
    smallFileSize = 10
    obj = externalMergeSort()
    obj.splitFiles(largeFileName, smallFileSize)
    """ Useslower level functions without any python Libraries . Better to understand it """
    # print(obj.mergeSortedtempFiles_low_level())
    """Pythonic way - Uses a generator """
    sortedCompleteData = obj.mergeSortedtempFiles()
    #obj.iterateSortedData(sortedCompleteData)
    obj.createSortedFile(sortedCompleteData)
