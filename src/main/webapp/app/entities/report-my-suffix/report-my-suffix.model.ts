import { BaseEntity } from './../../shared';

export const enum STATUS {
    'PENDIENTE',
    'ATENDIDO'
}

export const enum REWORK {
    'NO',
    'SI'
}

export class ReportMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public tsm?: string,
        public domain?: string,
        public schedule?: string,
        public server?: string,
        public datetimefalla?: any,
        public status?: STATUS,
        public rework?: REWORK,
        public error?: string,
        public code?: string,
        public datetimereworkini?: any,
        public datetimereworkfin?: any,
        public ticket?: string,
        public comment?: string,
        public user?: string,
        public act?: string,
    ) {
    }
}
