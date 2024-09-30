export type UserLoginType = {
  username: string
  password: string
}

export type UserType = {
  username: string
  password: string
  name: string
  role: string
  roleId: string
  permissions: string | string[]
}
