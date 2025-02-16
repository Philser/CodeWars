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
  ' ': '',
  '···−−−···': 'SOS',
};

export function decodeBits(bits: string) {
  let bitsStripped = bits.slice(bits.indexOf('1'), bits.lastIndexOf('1') + 1);
  let sampleRate = determineSampleRate(bitsStripped);

  return bitsStripped
    .replaceAll('1'.repeat(sampleRate * 3), '-')
    .replaceAll('1'.repeat(sampleRate), '.')
    .replaceAll('0'.repeat(sampleRate * 7), '   ')
    .replaceAll('0'.repeat(sampleRate * 3), ' ')
    .replaceAll('0', '');
}

function determineSampleRate(bits: string): number {
  let shortestSequence = bits;
  let currSequence = bits[0];
  for (let i = 1; i < bits.length; i++) {
    if (bits[i] !== bits[i - 1]) {
      if (currSequence.length < shortestSequence.length) {
        shortestSequence = currSequence;
      }
      currSequence = bits[i];
    } else {
      currSequence += bits[i];
    }
  }

  return shortestSequence.length;
}

export function decodeMorse(morseCode: string): string {
  let sentence = '';

  let code = morseCode.trim();
  code.split('   ').map((word) => {
    word.split(' ').forEach((char) => {
      sentence += MORSE_CODE[char];
    });
    sentence += ' ';
  });

  return sentence.trim();
}

console.log(
  decodeMorse(
    decodeBits(
      '1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011'
    )
  )
);
