def in_array(array1, array2):
    
    posList = []
    returnArray = []
    
    for x in range(0, len(array1)):
        for string in array2:
            stringToFind = array1[x]
            
            if string.find(stringToFind) == -1:
                continue
            else:
                if array1[x] not in returnArray:
                    returnArray.append(array1[x])          

    returnArray.sort()
    
    return returnArray
