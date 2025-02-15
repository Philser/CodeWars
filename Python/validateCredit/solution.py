def validate(n):
    numberString = str(n)
    digitCount = len(numberString)
    newDigits = []
    
    #step 1
    for digit in numberString:
        newDigits.append(digit)
    
    if digitCount % 2 == 0:
        i=0
    else:
        i=1
    
    while i<digitCount:
        newDigit = int(numberString[i])*2
        #step 2
        if newDigit > 9:
            newDigit -= 9
        newDigits[i]=newDigit
        i+=2
        
    #step 3
    sum = 0
    for digit in newDigits:
        sum += int(digit)
    
    #step 4
    if sum % 10 == 0:
        return True
    else:
        return False
