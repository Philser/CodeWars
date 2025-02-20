export function dirReduc(arr: string[]): string[] {
  const opposites = {
    NORTH: 'SOUTH',
    SOUTH: 'NORTH',
    WEST: 'EAST',
    EAST: 'WEST',
  };

  let wasReduced = true;
  let oldArr = arr;
  let newArr = [];
  while (wasReduced) {
    wasReduced = false;
    for (let i = 0; i < oldArr.length; i++) {
      if (
        i !== oldArr.length - 1 &&
        oldArr[i + 1] === opposites[oldArr[i] as string]
      ) {
        i += 1;
        wasReduced = true;
        continue;
      }
      newArr.push(oldArr[i]);
    }

    oldArr = newArr;
    newArr = [];
  }

  return oldArr;
}

export function dirReduc2(arr: string[]): string[] {
  const opposites = {
    NORTH: 'SOUTH',
    SOUTH: 'NORTH',
    WEST: 'EAST',
    EAST: 'WEST',
  };

  let stack = [arr[0]];
  for (let i = 1; i < arr.length; i++) {
    if (arr[i] === opposites[stack[stack.length - 1]]) {
      stack.pop();
      continue;
    }
    stack.push(arr[i]);
  }

  return stack;
}

console.log(
  dirReduc2(['NORTH', 'SOUTH', 'SOUTH', 'EAST', 'WEST', 'NORTH', 'WEST'])
);

console.log(dirReduc2(['NORTH', 'SOUTH', 'SOUTH', 'EAST', 'WEST', 'NORTH']));
console.log(dirReduc2(['NORTH', 'EAST', 'WEST', 'SOUTH', 'WEST', 'WEST']));
console.log(dirReduc2(['NORTH', 'WEST', 'SOUTH', 'EAST', 'BLA']));
console.log(dirReduc2(['EAST']));
