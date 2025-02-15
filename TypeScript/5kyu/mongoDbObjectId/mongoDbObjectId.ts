var Mongo = {
  isValid: function (s) {
    if (typeof s !== 'string') return false;
    if (s.length !== 24) return false;
    if (s.match(/[A-Z]/)) return false;
    if (s.match(/\W/)) return false;
    if (
      new Date(parseInt(s.substring(0, 8), 16) * 1000).toString() ===
      'Invalid Date'
    )
      return false;
    if (s.substring(8).toString().match(/[g-z]/)) return false; // weed out invalid hex values

    return true;
  },

  getTimestamp: function (s) {
    if (!Mongo.isValid(s)) return false;

    return new Date(parseInt(s.substring(0, 8), 16) * 1000);
  },
};
