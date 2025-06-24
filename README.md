# Smart Parking Management System (SPMS)
*Microservice-Based Application*

## Overview

The Smart Parking Management System (SPMS) is a cloud-native, microservice-based platform that solves urban parking challenges through real-time management and monitoring of parking spaces. Users can locate, reserve, and pay for parking while parking owners manage spaces dynamically.

## Business Problem

Urban parking challenges include traffic congestion, fuel wastage, emissions, and reduced mobility. Manual and static systems are inefficient and non-scalable.

## Solution

SPMS provides:
- Dynamic space allocation and real-time tracking
- Digital transactions and booking receipts
- Scalable microservice architecture
- IoT integration capabilities

## Technology Stack

| Technology | Purpose |
|------------|---------|
| **Spring Boot** | Core microservices framework |
| **Spring Cloud Eureka** | Service registry and discovery |
| **Spring Cloud Config** | Centralized configuration management |
| **Spring Cloud Gateway** | API Gateway for request routing |
| **Postman** | API testing and validation |

## Microservices Architecture

### Infrastructure Services
- **Eureka Server**: Service registration and discovery
- **Config Server**: Centralized configuration management
- **API Gateway**: Single entry point for client requests

### Business Services
- **Parking Space Service**: Manage parking spots, reservations, and availability
- **Vehicle Service**: Handle vehicle registration and entry/exit tracking
- **User Service**: User management, authentication, and booking history
- **Payment Service**: Process payments and generate digital receipts

## Project Structure

```
spms/
├── eureka-server/
├── config-server/
├── api-gateway/
├── parking-space-service/
├── vehicle-service/
├── user-service/
├── payment-service/
├── docs/screenshots/
├── postman_collection.json
└── README.md
```

## Getting Started

### Prerequisites
- Java 11+, Maven 3.6+, Git, Postman

### Quick Start
1. Clone repository
2. Start services in order: Eureka → Config → Gateway → Business Services
3. Verify at Eureka Dashboard: `http://localhost:8761`

## Key Features

### For Drivers
- Real-time parking search and reservation
- Secure payments and digital receipts
- Booking history

### For Parking Owners
- Dynamic space management
- Real-time updates and analytics

### System Features
- Scalable microservice architecture
- Service discovery and load balancing
- Comprehensive API coverage
