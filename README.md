# BorderCountryInfoRestAPI
API which is used to fetch the border countries and their details for the given country name

# Sample API Format
http://localhost:8080/api/v1/countryDetails?name=India

# Sample API Response
[response.json](https://github.com/premsn-1226/BorderCountryDetailsAPI/files/15285356/response.json)

[
    {
        "result": "Bordering Countries of India are Bangladesh, Bhutan, Myanmar, China, Nepal, Pakistan.",
        "borderCountries": [
            "Bangladesh",
            "Bhutan",
            "Myanmar",
            "China",
            "Nepal",
            "Pakistan"
        ],
        "countriesInfo": [
            {
                "officialLanguage": false,
                "carDrivingSide": true,
                "approxDistance": "884 Miles",
                "officialName": "Bangladesh"
            },
            {
                "officialLanguage": false,
                "carDrivingSide": true,
                "approxDistance": "761 Miles",
                "officialName": "Bhutan"
            },
            {
                "officialLanguage": false,
                "carDrivingSide": false,
                "approxDistance": "1334 Miles",
                "officialName": "Myanmar"
            },
            {
                "officialLanguage": false,
                "carDrivingSide": false,
                "approxDistance": "2348 Miles",
                "officialName": "China"
            },
            {
                "officialLanguage": false,
                "carDrivingSide": true,
                "approxDistance": "498 Miles",
                "officialName": "Nepal"
            },
            {
                "officialLanguage": true,
                "carDrivingSide": true,
                "approxDistance": "428 Miles",
                "officialName": "Pakistan"
            }
        ]
    }
]
