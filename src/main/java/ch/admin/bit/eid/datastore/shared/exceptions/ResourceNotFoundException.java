/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bit.eid.datastore.shared.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String id) {
        super("Resource with id '%s' not found".formatted(id));
    }
}
