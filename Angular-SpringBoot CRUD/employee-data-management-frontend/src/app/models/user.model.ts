// User Response DTO (matches backend UserResponseDto)
export interface UserResponse {
  id: number;
  companyName: string;
  email: string;
  tenantSchema: string;
}

// Register Request DTO (matches backend RegisterRequestDto)
export interface RegisterRequest {
  companyName: string;
  email: string;
  password: string;
}

// Login Request DTO (matches backend LoginRequestDto)
export interface LoginRequest {
  email: string;
  password: string;
}