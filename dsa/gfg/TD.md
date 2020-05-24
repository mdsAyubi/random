# TD

## Q1

Array A1 = [3,6,10,20, , , ]
Array A2 = [4,8,15]

Sort the array into the first array such that it is sorted in the end.
Constraint - S(1)

Two counters i, j for A1 and A2
Compare the lements at A[i] and A2[j]

## Vending Machine

- Features
  - Accept money
    - interface with the hardware to take the inputs
    - ingest money and recongnise
    - maintain the amount of money ingested till now and the item selected
    - give the current state
    - reset the current state after a succeesful/unsuccessful transaction
  - Vend product
    - get the current state from the AM module
    - interface with the layout manager for the item selected
    - call the AM module to reste the state
  - Inventory update
    - for an item
      - update the item update
      - update the layout
- High level components

  - InputManager - to take money, recognise it, accept it, or reject it
  - StateManager - for an item how much is ingested, what is the state of the machine - ingesting, vending, error etc
  - VendorManager - to send the item to the bin
  - InventoryManager

- DB Schema

  - Tables
    - vending_transation
    - items
    - layout_manager

- API contract
  InventoryManager - update(Item item) - updateItemDetails(Item item) - setup()

  VendingManager - vend(Item item) - insert(Denomination money)

- Tech Stack

  - Language
    - depends upong the team expertise and current tech stack in the firm
  - Database
    - POstgres, Mysql or any RDBMS will work as long as data is less than 5TB per year
  - ORM
    - Again depends on language of choice
  - Build deploy/CI/CD
    - Jenins, spinnaker, AWS code deploy could work
  - Dev sanity

- Non functional reqs
  - extensibility - to work with any vending OEM hardware
  - reliability - error rate based SLO
  - availability - machine should be able to work with minimal internet connection
  - stability - should in no case be ingesting money and not vending
