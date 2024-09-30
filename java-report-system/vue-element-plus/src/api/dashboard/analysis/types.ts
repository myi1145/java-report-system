export type AnalysisTotalTypes = {
  visitsSum: number
  // messages: number
  transplantSum: number
  moneys: number
  inHospSum: number
}

export type AnalysisTransplantTypes = {
  oneClinic: number
  // messages: number
  twoClinic: number
  triClinic: number
  diffDisease: number
}

export type AnalysisInHospitalTypes = {
  beHosp: number
  // messages: number
  inHosp: number
  outHosp: number
}

export type RateType = {
  value: number
  name: string
}

export type WeeklyUserActivity = {
  value: number
  name: string
}

export type MonthlySales = {
  // name: string
  // estimate: number
  // actual: number
  day: string
  thisSum: number
  leftSum: number
}
