#TEAM WATER: Peyton Cysewski, Matthew Petersen, Chandler Puckett, Mike Wohl

##Software Requirements

###Vision
Our vision for the app a project that provides users with meaningful, useful plant data from Trefle API. Our user might be a casual gardener, or someone looking to grow edible crops in their yard, or an expert looking to fill a niche. 
We plan to implement user profiles so that our users can share their "gardens" with others who are passionate and interested in the plant world.
This solves various pain points -- plant data can be organized by returning only plants that meet criteria for growing conditions, tailoring your search results to your local climate.
It will also help gardeners to solve aesthetic pain points. "I have a space in my garden; I need a plant that flowers at a certain time and is a specific height and color to match my garden's theme".
Our app wil be responsively designed so that is equally functional and enjoyable to use across devices.


###Scope IN
The app will have a random plant selection on the landing page.
The app will allow users to search Trefle API for plants based on various criteria such as flowering season, height, color, edibility, and others.
The app will allow users to create a unique individual profile.
The app will allow users to save individual results to a "virtual garden" associated with their profile.
The app will allow users to delete plants from the virtual garden.

###Scope OUT
The web app will scale to function on mobile and tablet devices, but will not be an iOS or Android app.
The app will not be able to add plants from outside of the Trefle API -- no user created or identified plants.

###MVP
Creating an account/profile, querying the API for plants, and saving plants to user profiles.

###Stretch Goals
Social functionality. We'd like users to be able to share their gardens with one another, and to interact and "Like" other gardens.
Multiple gardens per user --  front yard, back yard, etc.

###Functional Requirements

User can create an account.
User can search plant data.
User can save and remove plants from their profile.

###Data Flow
A random set of plants will display on the landing page in an image carousel. We'll need to query the Trefle API on load for these random returns. Users should
be able to sign up from the landing page, and then log in. From the logged in state, users should be able to use the search feature to select or deselect what criteria
they want to search by, and choose values to search by those criteria. From the search results, users will be able to save the selected plants into their garden.

###Non-Functional Requirements

Security: User information should be encrypted other than their usernme. We'll use a password encoder to ensure that private data is not visible to the public.
Testability: 

