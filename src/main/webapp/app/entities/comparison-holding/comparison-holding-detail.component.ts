import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IComparisonHolding } from 'app/shared/model/comparison-holding.model';

@Component({
    selector: 'jhi-comparison-holding-detail',
    templateUrl: './comparison-holding-detail.component.html'
})
export class ComparisonHoldingDetailComponent implements OnInit {
    comparisonHolding: IComparisonHolding;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ comparisonHolding }) => {
            this.comparisonHolding = comparisonHolding;
        });
    }

    previousState() {
        window.history.back();
    }
}
