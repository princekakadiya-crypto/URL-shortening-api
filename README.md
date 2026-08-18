# URL Shortener

# Hardikbhai Gohel 

## Purchase APIs 

- POST   /api/v1/purchases
    Create purchase

- GET    /api/v1/purchases
    Get own purchase history

- GET    /api/v1/purchases/{id}
    Get purchase details

- POST   /api/v1/purchases/{id}/confirm
    Confirm/process purchase payment

- /api/v1/purchases/qr/{urlid} 
    Purchase QR CODE
 
 
## BusinessOffers APIs
- GET /api/v1/offers
   Get All Offers
- POST /api/v1/offers
   Create New BusinessOffer
- GET /api/v1/offers/{ID}
   Get Bussiness Offer By ID

## Admin - USer Management APIs
- GET /api/v1/admin/users
  Get All Users
- DELETE /api/v1/admin/users/{id}
   Delete User By ID
- GET /api/v1/admin/users/{id}
   Get User By ID