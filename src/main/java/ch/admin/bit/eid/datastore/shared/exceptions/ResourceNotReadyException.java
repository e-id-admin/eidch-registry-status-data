/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bit.eid.datastore.shared.exceptions;

public class ResourceNotReadyException extends RuntimeException {

    public ResourceNotReadyException(String id) {
        super("Resource with id '%s' is not ready for processing.".formatted(id));
    }
}
