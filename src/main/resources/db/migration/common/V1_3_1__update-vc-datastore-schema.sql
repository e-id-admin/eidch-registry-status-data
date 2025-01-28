/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

ALTER TABLE vc_entity
    RENAME COLUMN decoded_vc to raw_vc;
ALTER TABLE vc_entity
    RENAME COLUMN encoded_vc to vc_payload;
