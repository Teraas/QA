#Design

#High Level Design
Overview - A Social Media app targeting the Indian family system. To help stay connected with the whole family of any Clan/Caste/Group/FamilyName/Community.
Features -
User Profile
User Login/Registration
User Timeline
User Family Tree view - to be the USP
Public Pages
Private and Controlled Privacy Page.
User Search
Add a relationship for the User.
Create a post(Text/Photo/photos) - MVP to not have video.
User count target - 10M

Design Pillers - High Availability, Resiliency with fault tolerance and recovery, Reliability, Durability, Cost Optimization

Servers to host the services - Cloud (AWS, Azure) + utilise Pi 4 if enough for MVP.

System to be read heavy, but significant writes.

Daily users - 1M
Daily reads - 5M
Daily writes - 0.5M
Read:write - 10:1
Monthly rights each user- 10posts (text or photos) - avg size 500kb
Total annual storage needs - 10M *10*12*500kb -- 60,00,00kb -- 6PB
Daily writes - 0.5M*500kb=250gb
Daily reads - 2.5TB
Data models:
SQL - User/Relation table, PostMetadata*
NoSQL - Timeline, FamilyTree

#LLD
Classes:
User
Timeline
FamilyTree
Search
Profile
Post(Interface) - ImagePost, TextPost
UserRelation
RelationType (enum)

MVP Services:
Auth - /authenticate
UserService -> create, update,getUser/{userId}, createRelation, getUserRelations/{userId}, getAllUsers(private endpoint)
TimelineService -> getTimeline
TreeService -> getUserTree/{userId}
UserSearchService -> findUser
UserProfileService -> getProfile, updateProfile
PostService -> createPost, UpdatePost, deletePost, SharePost
LikeService

MVP+ services:
Public Pages
Private and Controlled Privacy Page.
DuplicateUserDetection
DuplicateUserRemoval

Class structures ---------
User {
FirstName, SurName, UserId, Email, Location, Status.
}
UserRelation {
UserXid, UserYid, Relation, UniqueId (Identifier to uniquely identify relation - X to Y. Keep only 1 record for relation between 2 users)
}
enum RelationType{}

#Data Models
SQL:
Users table (id, email, password, firstName, lastName, status, locationid)
UserRelations table (UserXid, UserYid, Relation, UniqueId)
UserDetails

NoSQL:
UserTimeline
FamlyTree


