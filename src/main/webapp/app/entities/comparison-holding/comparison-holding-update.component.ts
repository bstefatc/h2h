import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IComparisonHolding } from 'app/shared/model/comparison-holding.model';
import { ComparisonHoldingService } from './comparison-holding.service';

@Component({
    selector: 'jhi-comparison-holding-update',
    templateUrl: './comparison-holding-update.component.html'
})
export class ComparisonHoldingUpdateComponent implements OnInit {
    comparisonHolding: IComparisonHolding;
    isSaving: boolean;

    constructor(protected comparisonHoldingService: ComparisonHoldingService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ comparisonHolding }) => {
            this.comparisonHolding = comparisonHolding;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.comparisonHolding.id !== undefined) {
            this.subscribeToSaveResponse(this.comparisonHoldingService.update(this.comparisonHolding));
        } else {
            this.subscribeToSaveResponse(this.comparisonHoldingService.create(this.comparisonHolding));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IComparisonHolding>>) {
        result.subscribe((res: HttpResponse<IComparisonHolding>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
