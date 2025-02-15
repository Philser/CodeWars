const MORSE_CODE = {
    '.-': 'A',
    '-...': 'B',
    '-.-.': 'C',
    '-..': 'D',
    '.': 'E',
    '..-.': 'F',
    '--.': 'G',
    '....': 'H',
    '..': 'I',
    '.---': 'J',
    '-.-': 'K',
    '.-..': 'L',
    '--': 'M',
    '-.': 'N',
    '---': 'O',
    '.--.': 'P',
    '--.-': 'Q',
    '.-.': 'R',
    '...': 'S',
    '-': 'T',
    '..-': 'U',
    '.--': 'W',
    '-..-': 'X',
    '-.--': 'Y',
    '--..': 'Z',
    '···−−−···': 'SOS',
};

export function decodeMorse(morseCode: string): string {
    let sentence = '';
    let code = morseCode.trim();
    code.split('   ').map((word) => {
        word.split(' ').forEach((char) => {
            sentence += MORSE_CODE[char];
        });

    });

    return sentence.trim();
}

console.log(decodeMorse('.... . -.--   .--- ..- -.. .'));
console.log(decodeMorse('.-'));
console.log(decodeMorse('   .   . '));
