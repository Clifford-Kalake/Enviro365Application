API Name: Enviro365 File Processing API

Base URL: http://localhost:8080 

Endpoints

Upload Environmental Data

URL: /upload

Method: POST

Input Parameters:

file (MultipartFile): Required. The text file containing environmental data.

Request Content-Type: multipart/form-data

Response Formats:

Success (200 OK):

Content-Type: application/json

Example:

[

    {
    
        "id": 1,
        
        "data": "Sample Data Line 1"
        
    },
    
    {
    
        "id": 2,
        
        "data": "Sample Data Line 2"
        
    }
    
]

Error (400 Bad Request):

Content-Type: application/json

Example:

{

    "status": 400,
    
    "message": "Error message (e.g., File is empty, Invalid format at line 3, etc.)"
    
}

Error (500 Internal Server Error)

Content-Type: application/json

Example:

{

   "status": 500,
   
   "message": "An unexpected error occurred"
   
}


