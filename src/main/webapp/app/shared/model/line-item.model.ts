import { IAsset } from 'app/shared/model//asset.model';

export interface ILineItem {
    id?: number;
    feedback?: string;
    assetOwed?: IAsset;
}

export class LineItem implements ILineItem {
    constructor(public id?: number, public feedback?: string, public assetOwed?: IAsset) {}
}
