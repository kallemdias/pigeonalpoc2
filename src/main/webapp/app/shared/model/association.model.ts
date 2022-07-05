import { IRacingPlan } from '@/shared/model/racing-plan.model';

export interface IAssociation {
  id?: number;
  name?: string;
  shortName?: string;
  email?: string | null;
  phone?: string | null;
  addressLine1?: string | null;
  addressLine2?: string | null;
  city?: string | null;
  country?: string | null;
  fbLink?: string | null;
  racingPlans?: IRacingPlan[] | null;
}

export class Association implements IAssociation {
  constructor(
    public id?: number,
    public name?: string,
    public shortName?: string,
    public email?: string | null,
    public phone?: string | null,
    public addressLine1?: string | null,
    public addressLine2?: string | null,
    public city?: string | null,
    public country?: string | null,
    public fbLink?: string | null,
    public racingPlans?: IRacingPlan[] | null
  ) {}
}
