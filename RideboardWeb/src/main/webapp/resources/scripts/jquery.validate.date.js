$.validator.methods.date = function (value, element) {
    var dateRegex = /^(0?[1-9]|[12]\d|3[01])\/(0?[1-9]|10|11|12)\/(19|20)\d\d(\D.*)?$/;
    return this.optional(element) || dateRegex.test(value);
};