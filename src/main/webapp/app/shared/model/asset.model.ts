export interface IAsset {
    id?: number;
    name?: string;
    value?: number;
}

export class Asset implements IAsset {
    constructor(public id?: number, public name?: string, public value?: number) {}
}
