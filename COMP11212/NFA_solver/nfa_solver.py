# -*- coding: utf-8 -*-
"""
Finds if a word can go through an NFA. Prints the sequence of visited states
if a route can be found.

The example NFA we'll use accepts all words that start and end with 'a':
                                   a                                
    +––––––––––––––––––––––––––––––––––––––––––––––––––––––––––+    
    |                                                          |    
    |                                                          |    
    |                                                          |    
+–––▲––––+                   +––––––––+                  +–––––▼–––+
|        |        a          |        |       a          ||-------||
|   0    >–––––––––––––––––––>    1   >––––––––––––––––––>|    2  ||
|        |                   |        |                  ||-------||
+––––––––+                   +––▼––▲––+                  +–––––––––+
                                |  |                                
                                |  |                                
                                |  |                                
                                |  |                                
                                +––+                                
                                a,b                               

"""
# The transitions between states
transitions = {
	"0": {
		"a":["1","2"]
	},
	"1": {
		"a":["1","2"],
		"b":["1"]
	},
	"2": {}
}
# The accepting states
acceptingStates = ["2"]

def getTransitions(state, letter):
	"""
	Get the list of next states accessible from this state
	"""
	return transitions.get(state, {}).get(letter,[])

def evaluateWord(word, currentState="0", path=""):
	"""
	Is this word accepted by the NFA?
	0 is the default start state
	"""
	if not path:
		path = currentState

	# If the word is empty, then we've reached a base case
	if not word:
		if currentState in acceptingStates:
			# If the current state is an accepting state, return the path we made
			return path
		else:
			# Otherwise, we've reached a dead end
			return None

	# Get the list of next states we can go into for this letter
	nextStates = getTransitions(currentState, word[0])

	# Iterate through those states
	for state in nextStates:
		# Evaluate the remaining word from the new state
		foundPath = evaluateWord(word[1:], state, path + state)
		if foundPath:
			return foundPath


print evaluateWord("aba")
print evaluateWord("bbbbb")
