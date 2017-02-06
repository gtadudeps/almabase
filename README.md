# GitHub API JAVA Client 
This is a Java API client which determines finds top 5 most popular repositories of a given organization on github (Eg:https://github.com/google) based on the number of forks. For each such repo find the top 3 committees and their commit counts.

It is broken down into 4 modules
 
1. Beans - used to map GitHub API response 
2. Services - Service do majority of the part getting and processing data
3. Utilities- Which helps in grouping adapting to the required format 
4. Application Initializer - It is a part which initializes the whole of application
