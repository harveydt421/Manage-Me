export interface ILineItem {
    id?: number;
    feedback?: string;
    cleared?: boolean;
    assetOwedName?: string;
    assetOwedId?: number;
    separationApplicationId?: number;
}

export class LineItem implements ILineItem {
    constructor(
        public id?: number,
        public feedback?: string,
        public cleared?: boolean,
        public assetOwedName?: string,
        public assetOwedId?: number,
        public separationApplicationId?: number
    ) {
        this.cleared = false;
    }
}
