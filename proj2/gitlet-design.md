# Gitlet Design Document

**Name**: Simon Shephard


## Classes and Data Structures


### Main

Handles commands passing responsibility to other classes.

#### No Fields


### File / blob

Represenation of file object with file data and changes since commit.


### Commit

#### Fields

1. List of updated objects or current state
2. Timestamp / UUID/HASH
3. Message


### Repository

Representation of storage for commits / history / file metadata etc.

#### Fields

1. List of commits



## Algorithms

1. Hashing
2. Serialisation


## Persistence

Storage of files in directory
Storage of meta information as files in subdirectory e.g. .gitlet




