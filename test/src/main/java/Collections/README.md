#Hash
Suppose we want an array-like data structure with constant-time lookups, but we want to look up values based on arbitrary "keys," not just sequential "indices." We could allocate an array, and use a hash method to translate keys into array indices. That's the basic idea behind a hash map!
HashMap - unique key/values
HashMap<String, Integer> lightBulbToHoursOfLight = new HashMap<>();
A HashTable doesn't allow null keys or values; a HashMap does.
A HashTable is synchronized to prevent multiple threads from accessing it at once; a HashMap isn't.

HashSet - just keep unique keys 
Set<Character> unpairedCharacters = new HashSet<>();

Hash uses a hash function to translate a key to point to an index of an array. Same for HashMap and HashSet.
Typical hash functional would use MD5/sha1.

