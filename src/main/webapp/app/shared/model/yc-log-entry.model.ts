import { IRacingPlan } from '@/shared/model/racing-plan.model';

import { LogType } from '@/shared/model/enumerations/log-type.model';
export interface IYcLogEntry {
  id?: number;
  created?: Date | null;
  serviceName?: string | null;
  method?: string | null;
  step?: string | null;
  discription?: string | null;
  logType?: LogType | null;
  racingPlan?: IRacingPlan | null;
}

export class YcLogEntry implements IYcLogEntry {
  constructor(
    public id?: number,
    public created?: Date | null,
    public serviceName?: string | null,
    public method?: string | null,
    public step?: string | null,
    public discription?: string | null,
    public logType?: LogType | null,
    public racingPlan?: IRacingPlan | null
  ) {}
}
