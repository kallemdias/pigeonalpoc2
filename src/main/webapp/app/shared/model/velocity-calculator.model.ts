export interface IVelocityCalculator {
  id?: number;
  releaseDateTime?: Date | null;
  arrivalDateTime?: Date | null;
  distanceKM?: number | null;
  distanceM?: number | null;
  velocity?: number | null;
  velocityDispVal?: string | null;
}

export class VelocityCalculator implements IVelocityCalculator {
  constructor(
    public id?: number,
    public releaseDateTime?: Date | null,
    public arrivalDateTime?: Date | null,
    public distanceKM?: number | null,
    public distanceM?: number | null,
    public velocity?: number | null,
    public velocityDispVal?: string | null
  ) {}
}
