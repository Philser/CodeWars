def title_case(title, minor_words=""):
    
    if len(title) == 0:
        return ""
    
    minor_words = minor_words.lower().split(" ")
    
    title = title.split(" ");
    title_cased = ""
    
    for pos in range(0, len(title)):
        word = title[pos]
        
        if word.lower() in minor_words and pos != 0:
            word = word.lower()
        else:
            word = word.capitalize()
            
        title_cased += word          
        if pos != len(title)-1:
            title_cased += " "
        
    return title_cased
