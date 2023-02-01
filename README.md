# Hashing
Open Hashing Algorithm
Open Hashing Algorithm

What is separate chaining?

Separate chaining is a technique that stores keys in linked lists assigned to each slot of the hashing table. This allows multiple keys to hold the same hash value and not be probed to another slot. Therefore, a fixed hash table size is appropriate.

What does this program do?

1. Takes input from console, removes all characters that are not letters, and converts all letters to lowercase.
2. Creates Array of Linked Lists that hold 26 slots and fills with null values
2. Takes the first letter of every word and converts it to ASCII then redundantly mods it by 26 to get the key value
4. Stores the key inside appropriate linked list
5. Outputs number of occurrences of a word and the total number of comparisons performed for all words.
