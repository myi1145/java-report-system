import request from '@/axios'
import type {
  AnalysisTotalTypes,
  RateType,
  WeeklyUserActivity,
  MonthlySales,
  AnalysisTransplantTypes,
  AnalysisInHospitalTypes
} from './types'

export const getCountApi = (params: string): Promise<IResponse<AnalysisTotalTypes[]>> => {
  return request.get({ url: `/analysis/total?username=${params}` })
}

export const getTransplantApi = (params: string): Promise<IResponse<AnalysisTransplantTypes[]>> => {
  return request.get({ url: `/analysis/transplant?username=${params}` })
}

export const getInHospitalApi = (params: string): Promise<IResponse<AnalysisInHospitalTypes[]>> => {
  return request.get({ url: `/analysis/inHospital?username=${params}` })
}

export const getRateTypeApi = (params: string): Promise<IResponse<RateType[]>> => {
  return request.get({ url: `/analysis/getRateType?username=${params}` })
}

export const getWeeklyUserActivityApi = (
  params: string
): Promise<IResponse<WeeklyUserActivity[]>> => {
  return request.get({ url: `/analysis/weeklyUserActivity?username=${params}` })
}

export const getMonthlySalesApi = (params: string): Promise<IResponse<MonthlySales[]>> => {
  return request.get({ url: `/analysis/monthlySales?username=${params}` })
}
