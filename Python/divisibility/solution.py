def thirt(n):
    string_n = str(n)
    number_size = len(string_n)
    static_remainders = get_n_remainders(number_size)
    
    # multiply
    last_result = -1
    result = 0
    while(result != last_result):
        last_result = result
        result = 0
        for i in range(number_size - 1, -1, -1):
            digit = int(string_n[i])
            result += digit * static_remainders[number_size - i - 1]
            
        string_n = str(result)
        number_size = len(string_n)
        print(result)
    return result

def get_n_remainders(n):
    remainders = []
    for i in range(0, n):
        remainder = 10**i % 13
        remainders.append(remainder)
    #print(remainders)
    return remainders


thirt(144) # example
