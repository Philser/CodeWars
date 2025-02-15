export function toCamelCase(str: string): string {
  const re = /([a-zA-Z]+)/g;
  const match = str.match(re);
  if (!match) return str;

  for (let i = 0; i < match.length; i++) {
    if (i === 0) continue;

    match[i] = match[i][0].toUpperCase() + match[i].substring(1);
  }

  return match.join('');
}
