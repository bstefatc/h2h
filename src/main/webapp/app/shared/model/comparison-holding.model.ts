export interface IComparisonHolding {
    id?: number;
    name?: string;
    holdings?: string;
}

export class ComparisonHolding implements IComparisonHolding {
    constructor(public id?: number, public name?: string, public holdings?: string) {}
}
