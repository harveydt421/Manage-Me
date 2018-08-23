import { IAsset } from 'app/shared/model//asset.model';
import { ISeparationApplication } from 'app/shared/model//separation-application.model';

export interface ILineItem {
    id?: number;
    feedback?: string;
    assetOwed?: IAsset;
    separationApplication?: ISeparationApplication;
}

export class LineItem implements ILineItem {
    constructor(
        public id?: number,
        public feedback?: string,
        public assetOwed?: IAsset,
        public separationApplication?: ISeparationApplication
    ) {}
}
